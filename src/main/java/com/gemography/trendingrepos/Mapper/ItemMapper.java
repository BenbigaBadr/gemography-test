package com.gemography.trendingrepos.Mapper;

import com.gemography.trendingrepos.entity.ItemEntity;
import com.gemography.trendingrepos.entity.OwnerEntity;
import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.models.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper( ItemMapper.class );

    Item sourceToDestination(ItemEntity entity);

    ItemEntity destinationToSource(Item entity);

    Owner sourceToDestination(OwnerEntity entity);

    OwnerEntity destinationToSource(Owner owner);

    List<Item> sourceToDestination(List<ItemEntity> entity);

    List<ItemEntity> destinationToSource(List<Item> entity);

}
