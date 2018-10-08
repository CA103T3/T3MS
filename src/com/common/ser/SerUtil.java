package com.common.ser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.movie.model.MovieVO;

public class SerUtil {
    private SerStrategy_interface si;
    public SerUtil() {
        // TODO Auto-generated constructor stub
    }

    public void setStrategy(SerStrategy_interface si){
        this.si = si;
    }

    public void importOne(String action, String no) throws Exception {
        si.importOne(action, no);
    }

    public void importAll() throws Exception {
        si.importAll();
    }

    public Object findVOByNo(String no) {
        return si.findVOByNo(no);
    }

    public Object findVOByName(String name) {
        return si.findVOByName(name);
    }

    public List<Object> getAllVO() {
        return si.getAllVO();
    }

    public void export(Object vo) throws IOException {
        si.export(vo);
    }

    public void export(List<Object> list) throws IOException {
        si.export(list);
    }
    
    public void setServletContextRealPath(String servletContextRealPath) {
        si.setServletContextRealPath(servletContextRealPath);
    }

    public void importUpdateAll() throws Exception {
        si.importUpdateAll();
    }
}
