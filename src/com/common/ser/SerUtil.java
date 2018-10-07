package com.common.ser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SerUtil {
    private SerStrategy_interface si;
    public SerUtil() {
        // TODO Auto-generated constructor stub
    }

    public void setStrategy(SerStrategy_interface si){
        this.si = si;
    }

    public void importOne(String filename) {
        // TODO Auto-generated method stub

    }

    public void importAll() {
        // TODO Auto-generated method stub

    }

    public Object findVOByNo(String no) {
        Object obj = null;
        if(si != null) {
            obj = si.findVOByNo(no);
        }
        return obj;
    }

    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        return null;
    }

    public void export(Object vo) throws IOException {
        if(si != null) {
            si.export(vo);
        }
    }

    public void export(List<Object> list) {
        // TODO Auto-generated method stub

    }
    
}
