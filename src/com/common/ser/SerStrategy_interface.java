package com.common.ser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SerStrategy_interface {
    public void importOne(String action, String no) throws Exception;
    public void importAll() throws Exception;
    public void importUpdateAll() throws Exception;

    public Object findVOByNo(String no);
    public Object findVOByName(String name);
    public List<Object> getAllVO();
    public void export(Object vo) throws IOException;
    public void export(List<Object> list) throws IOException;

    public void setServletContextRealPath(String servletContextRealPath);
}
