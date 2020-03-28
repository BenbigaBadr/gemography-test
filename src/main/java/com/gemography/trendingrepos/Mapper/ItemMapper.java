package com.gemography.trendingrepos.Mapper;

import com.gemography.trendingrepos.entity.ItemEntity;
import com.gemography.trendingrepos.entity.OwnerEntity;
import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.models.Owner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ItemMapper {

    Item sourceToDestination(ItemEntity entity);

    List<Item> sourceToDestination(List<ItemEntity> entity);

    ItemEntity destinationToSource(Item entity);

    List<ItemEntity> destinationToSource(List<Item> entity);

    Owner sourceToDestination(OwnerEntity entity);

    OwnerEntity destinationToSource(Owner owner);

}
