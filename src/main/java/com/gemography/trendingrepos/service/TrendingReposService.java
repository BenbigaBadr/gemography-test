package com.gemography.trendingrepos.service;

import com.gemography.trendingrepos.Mapper.ItemMapper;
import com.gemography.trendingrepos.entity.ItemEntity;
import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.models.Items;
import com.gemography.trendingrepos.models.ReposAndCount;
import com.gemography.trendingrepos.repository.ItemRepository;
import com.gemography.trendingrepos.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrendingReposService {

    @Value("${trending-repos.url}")
    private String trendingRepositoryUrl;

    Logger logger = LoggerFactory.getLogger(TrendingReposService.class);

    @Autowired
    private ItemRepository itemRepository;

    private ItemMapper itemMapper = ItemMapper.INSTANCE;

    /**
     * récuppérer la liste des items à retourner dans l'API
     * @param language
     * @return
     */
    public List<Item> getRepos(String language) {
        assert language != null : "The language shouldn't be null";
        List<ItemEntity> itemEntities = itemRepository.findByLanguage(language);
        return itemMapper.sourceToDestination(itemEntities);
    }


    /**
     * récuppération du nombre de répértoires par language
     * @param language
     * @return
     */
    public Long count(String language) {
        assert language != null : "The language shouldn't be null";
        return itemRepository.countByLanguage(language);
    }

    /**
     * récuppération de la liste des languages
     * @return
     */
    @Cacheable("languages")
    public List<String> getAllLanguages() {
        return itemRepository.getAllLanguages();
    }

    /**
     * création des items dans la base de données
     * @param items
     * @return
     */
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "languages", allEntries = true)})
    public List<Item> create(List<Item> items) {
        logger.info("--- DEBUT : creating Items -----");
        List<ItemEntity> mappedEntities = itemMapper.destinationToSource(items);
        logger.info("--- FIN : creating Items -----");
        List<ItemEntity> entities = itemRepository.save(mappedEntities);
        return itemMapper.sourceToDestination(entities);
    }

    /**
     * regroupers les conteurs des répértoires par languages
     * @return
     */
    public Map<String, Long> getLanguagesCount() {
        Map<String, Long> itemCountPerLangage = new HashMap<>();
        List<String> languages = getAllLanguages();
        logger.info("--- DEBUT : build of the Item count per language -----");
        languages.stream().filter(language -> language != null).forEach(language -> {
            Long countPerLanguage = count(language);
            itemCountPerLangage.put(language, countPerLanguage);
        });
        logger.info("--- FIN : build of the Item count per language -----");
        return itemCountPerLangage;
    }

    /**
     * initialiser les items et les persister dans la base de données
     * @param creationDate
     * @return
     * @throws Exception
     */
    public List<Item> initItems(@RequestParam("date") String creationDate) throws Exception {
        DateUtil.parse(creationDate);
        RestTemplate restTemplate = new RestTemplate();
        Items result = restTemplate.getForObject(MessageFormat.format(trendingRepositoryUrl, creationDate), Items.class);
        if (!CollectionUtils.isEmpty(result.getItems()))
            return create(result.getItems());
        else
            return null;
    }

    /**
     * regrouper les répertoires par languages
     * @return
     */
    public Map<String, List<Item>> getLanguageRepos()
    {
        Map<String,List<Item>> itemsPerLangage = new HashMap<>();
        List<String> languages = getAllLanguages();
        logger.info("--- DEBUT : build of the repos list and count Per Lanuage -----");
        languages.stream().filter(language -> language != null).forEach(language -> {
            itemsPerLangage.put(language, getRepos(language));
        });
        logger.info("--- FIN : build of the repos list and count Per Lanuage -----");
        return itemsPerLangage;
    }

    /**
     * regrouper les répértoires ainsi ques ses conteurs par languages
     * @return
     */
    public Map<String, ReposAndCount> getReposAndTheirCountsPerLanguage()
    {
        Map<String, ReposAndCount> reposAndCountsPerLangage = new HashMap<>();
        List<String> languages = getAllLanguages();
        logger.info("--- DEBUT : build of the repos list and count Per Lanuage -----");
        languages.stream().filter(language -> language != null).forEach(language -> {
            List<Item> countPerLanguage = getRepos(language);
            reposAndCountsPerLangage.put(language, new ReposAndCount(countPerLanguage));
        });
        logger.info("--- FIN : build of the repos list and count Per Lanuage -----");
        return reposAndCountsPerLangage;
    }
}
