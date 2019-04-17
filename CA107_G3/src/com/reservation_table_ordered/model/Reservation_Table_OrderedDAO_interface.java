package com.reservation_table_ordered.model;

import java.util.List;
import java.util.Map;


public interface Reservation_Table_OrderedDAO_interface {
	
	public void insert(Reservation_Table_OrderedVO reservation_Table_OrderedVO);
    public void update(Reservation_Table_OrderedVO reservation_Table_OrderedVO);
    public void delete(String rto_no);
    public Reservation_Table_OrderedVO findByPrimaryKey(String rto_no);
    public List<Reservation_Table_OrderedVO> getAll();
    public List<Reservation_Table_OrderedVO> getAll(Map<String, String[]> map);

}
