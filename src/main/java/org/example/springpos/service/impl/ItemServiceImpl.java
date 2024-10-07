package org.example.springpos.service.impl;

import jakarta.transaction.Transactional;
import org.example.springpos.dao.ItemDAO;
import org.example.springpos.dto.ItemDTO;
import org.example.springpos.entity.Item;
import org.example.springpos.exception.DataPersistException;
import org.example.springpos.service.ItemService;
import org.example.springpos.util.AppUtil;
import org.example.springpos.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDAO itemDAO;

    @Autowired Mapping mapping;


    @Override
    public void save(ItemDTO itemDTO) {
        itemDTO.setId(AppUtil.generateItemId());
        Item saveitem = itemDAO.save(mapping.toItemEntity(itemDTO));
        if (saveitem == null){
            throw new DataPersistException("Item not saved");
        }
    }

    @Override
    public void delete(String id) {
        if (!itemDAO.existsById(id)){
            throw new DataPersistException("Item not found");
        }else {
            itemDAO.deleteById(id);
        }
    }

    @Override
    public void update(String id, ItemDTO dto) {
        Optional<Item> item = itemDAO.findById(id);
        if (item == null){
            throw new DataPersistException("Item not found");
        }else {
            item.get().setName(dto.getName());
            item.get().setQty(dto.getQty());
            item.get().setPrice(dto.getPrice());
        }
    }

    @Override
    public ItemDTO get(String id) {
        if (itemDAO.existsById(id)) {
            Item item = itemDAO.getReferenceById(id);
            return mapping.toItemDto(item);
        }else {
            throw new DataPersistException("Item not found");
        }
    }

    @Override
    public List<ItemDTO> getAll() {
        return mapping.toItemList(itemDAO.findAll());
    }
}
