package com.boxoffice.model;

import java.util.List;

public interface BoxOfficeDAO_interface {
    public String insert(BoxOfficeVO boVO);
    public void update(BoxOfficeVO boVO);
    public void delete(String ranking_no);
    public BoxOfficeVO findByPrimaryKey(String ranking_no);
    public List<BoxOfficeVO> getAll();
}
