package com.ticketType.model;

import java.util.List;

public interface TypeDAO_interface {
    public String insert(TypeVO typeVO);
    public void update(TypeVO typeVO);
    public void delete(String type_no);
    public TypeVO findByPrimaryKey(String type_no);
    public List<TypeVO> getAll();
}
