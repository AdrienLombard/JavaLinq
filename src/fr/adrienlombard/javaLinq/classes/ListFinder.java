package fr.adrienlombard.javaLinq.classes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListFinder {

	private List<? extends Object> list;
	private String query;
	
	public ListFinder(List<?> list, String query) {
		this.list = list;
		this.query = query;
	}
	
	public List<? extends Object> construct() throws MalformedQueryException {
		
		List<Object> res = new CopyOnWriteArrayList<Object>();
		
		for(Object o : list) {
			res.add(o);
		}
		
		String[] select = this.query.trim().toLowerCase().split("&&");
		
		for(int i = 0; i < select.length; i++) {
			select[i] = select[i].trim();
		}
		
		List<Condition> conds = new ArrayList<Condition>();
		
		for(String comp : select) {
			int infEq = comp.indexOf("<=");
			int supEq = comp.indexOf(">=");
			int inf = comp.indexOf("<");
			int sup = comp.indexOf(">");
			int eq = comp.indexOf("=");
			int dif = comp.indexOf("!=");
			
			String[] splitComp;
			
			if(infEq != -1) {
				splitComp = comp.split("<=");
				conds.add(new Condition("<=", splitComp[0].trim(), splitComp[1].trim()));
			}
			else if(supEq != -1) {
				splitComp = comp.split(">=");
				conds.add(new Condition(">=", splitComp[0].trim(), splitComp[1].trim()));
			}
			else if(inf != -1) {
				splitComp = comp.split("<");
				conds.add(new Condition("<", splitComp[0].trim(), splitComp[1].trim()));
			}
			else if(sup != -1) {
				splitComp = comp.split(">");
				conds.add(new Condition(">", splitComp[0].trim(), splitComp[1].trim()));
			}
			else if(dif != -1) {
				splitComp = comp.split("!=");
				conds.add(new Condition("!=", splitComp[0].trim(), splitComp[1].trim()));
			}
			else if(eq != -1) {
				splitComp = comp.split("=");
				conds.add(new Condition("=", splitComp[0].trim(), splitComp[1].trim()));
			}
		}
		
		for(Condition cond : conds) {
			for(Object o : list) {
				
				try {
					
					Field f = o.getClass().getDeclaredField(cond.getOperande1());
					f.setAccessible(true);
					
					switch(cond.getOperateur()) {
						case ">=":
							if(isNumeric(f.get(o))) {
								if(!verifCondition(">=", f.get(o), cond.getOperande2(), f.getType().getName())) {
									if(res.contains(o)) {
										res.remove(o);
									}
								}
							}
							break;
						case "<=":
							if(isNumeric(f.get(o))) {
								if(!verifCondition("<=", f.get(o), cond.getOperande2(), f.getType().getName())) {
									if(res.contains(o)) {
										res.remove(o);
									}
								}
							}
							break;
						case ">" :
							if(isNumeric(f.get(o))) {
								if(!verifCondition(">", f.get(o), cond.getOperande2(), f.getType().getName())) {
									if(res.contains(o)) {
										res.remove(o);
									}
								}
							}
							break;
						case "<" : 
							if(isNumeric(f.get(o))) {
								if(!verifCondition("<", f.get(o), cond.getOperande2(), f.getType().getName())) {
									if(res.contains(o)) {
										res.remove(o);
									}
								}
							}
							break;
						case "!=":
							if(f.get(o).toString().toLowerCase().equals(cond.getOperande2())) {
								if(res.contains(o)) {
									res.remove(o);
								}
							}
							break;
						case "=" :
							if(!f.get(o).toString().toLowerCase().equals(cond.getOperande2())) {
								if(res.contains(o)) {
									res.remove(o);
								}
							}
							break;
						default:
							break;
					}
					
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
		return res;
	}
	
	private boolean isNumeric(Object o) {
		
		if	(	o.getClass().getName().equals("java.lang.Integer") || 
				o.getClass().getName().equals("java.lang.Long") || 
				o.getClass().getName().equals("java.lang.Float") || 
				o.getClass().getName().equals("java.lang.Double") ||
				o.getClass().getName().equals("java.lang.Short") ||
				o.getClass().getName().equals("java.lang.Byte")
			) {
			return true;
		}
		
		return false;
	}
	
	private boolean verifCondition(String operateur, Object operande1, Object operande2, String type) {
		
		switch(operateur) {
		case ">=":
			switch(type) {
			case "int":
				if(Integer.valueOf(operande1.toString()) >= Integer.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "long":
				if(Long.valueOf(operande1.toString()) >= Long.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "float":
				if(Float.valueOf(operande1.toString()) >= Float.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "double":
				if(Double.valueOf(operande1.toString()) >= Double.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "short":
				if(Short.valueOf(operande1.toString()) >= Short.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "byte":
				if(Byte.valueOf(operande1.toString()) >= Byte.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			default:
				break;
			}
			break;
		case "<=":
			switch(type) {
			case "int":
				if(Integer.valueOf(operande1.toString()) <= Integer.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "long":
				if(Long.valueOf(operande1.toString()) <= Long.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "float":
				if(Float.valueOf(operande1.toString()) <= Float.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "double":
				if(Double.valueOf(operande1.toString()) <= Double.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "short":
				if(Short.valueOf(operande1.toString()) <= Short.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "byte":
				if(Byte.valueOf(operande1.toString()) <= Byte.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			default:
				break;
			}
			break;
		case ">":
			switch(type) {
			case "int":
				if(Integer.valueOf(operande1.toString()) > Integer.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "long":
				if(Long.valueOf(operande1.toString()) > Long.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "float":
				if(Float.valueOf(operande1.toString()) > Float.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "double":
				if(Double.valueOf(operande1.toString()) > Double.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "short":
				if(Short.valueOf(operande1.toString()) > Short.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "byte":
				if(Byte.valueOf(operande1.toString()) > Byte.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			default:
				break;
			}
			break;
		case "<":
			switch(type) {
			case "int":
				if(Integer.valueOf(operande1.toString()) < Integer.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "long":
				if(Long.valueOf(operande1.toString()) < Long.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "float":
				if(Float.valueOf(operande1.toString()) < Float.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "double":
				if(Double.valueOf(operande1.toString()) < Double.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "short":
				if(Short.valueOf(operande1.toString()) < Short.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			case "byte":
				if(Byte.valueOf(operande1.toString()) < Byte.valueOf(operande2.toString())) {
					return true;
				}
				else {
					return false;
				}
			default:
				return false;
			}
		default:
			break;
		}
		
		return false;
	}
	
}
