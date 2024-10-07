package org.example.springpos.controller;

import org.example.springpos.dto.ItemDTO;
import org.example.springpos.exception.DataPersistException;
import org.example.springpos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO){
        try {
            itemService.save(itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable("itemId") String itemId, @RequestBody ItemDTO itemDTO){
        try {
            itemService.update(itemId,itemDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{itemId}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable("itemId") String itemId){
        try {
            ItemDTO itemDTO = itemService.get(itemId);
            return new ResponseEntity<>(itemDTO,HttpStatus.OK);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<ItemDTO> getAllItems(){
        return itemService.getAll();
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") String itemId){
        try {
            itemService.delete(itemId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
