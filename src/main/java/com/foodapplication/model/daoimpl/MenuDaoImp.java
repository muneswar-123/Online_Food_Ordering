package com.foodapplication.model.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodapplication.model.dao.MenuDao;
import com.foodapplication.model.model.Menu;


public class MenuDaoImp implements MenuDao {
	
	List<Menu> menuList = new ArrayList<Menu>();
	private static final String INSERT_QUERY = "insert into `menu`(`restaurant_id`, `menu_name`, `menu_description`, `price`, `rating`, `image`) values (?, ?, ?, ?, ?, ?)";
	private static final String CHECK_RID_QUERY = "SELECT COUNT(*) FROM `restaurant` WHERE `restaurant_id` = ?";
	private static final String FETCH_ALL_QUERY = "select * from menu";
	private static final String FETCH_SPECIFIC = "select * from `menu` where `restaurant_id` = ?";
	private static final String FETCH_BY_ID = "select * from `menu` where `menu_id` = ?";
	private static final String UPDATE_QUERY = "update menu set price = ? where menu_id = ?";
	private static final String DELETE_QUERY = "delete from menu where menu_id = ?";
	
	//inserting data to the tables ....................................................
    private static final String FETCH_NON_VEG = "select * from `nonveg`";
    private static final String FETCH_VEG = "select * from `veg`";
    private static final String FETCH_BIRYANI_SPECIALS = "select * from `biryani_specials`";
    private static final String FETCH_PIZZA = "select * from `pizza`";
    private static final String FETCH_BUGGER = "select * from `burger`";
    private static final String FETCH_DELIGHTS= "select * from `delights`";
    private static final String FETCH_DESSERTS= "select * from `desserts`";
    
    // fetching data from tables.........................................................
	private static final String FETCH_BY_NON_VEG_ID = "select * from `nonveg` where `menu_id` = ?";
	private static final String FETCH_BY_VEG_ID = "select * from `veg` where `menu_id` = ?";
	private static final String FETCH_BY_BIRYANI_SPECIALS_ID = "select * from `biryani_specials` where `menu_id` = ?";
	private static final String FETCH_BY_PIZZA_ID = "select * from `pizza` where `menu_id` = ?";
	private static final String FETCH_BY_BURGER_ID = "select * from `burger` where `menu_id` = ?";
	private static final String FETCH_BY_DELIGHTS_ID = "select * from `delights` where `menu_id` = ?";
	private static final String FETCH_BY_DESSERTS_ID = "select * from `desserts` where `menu_id` = ?";
	
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/foodclone";;
	private String username = "root";
	private String password = "Muneswar@123";
	private PreparedStatement pstmt;
	private int res;
	private ResultSet result;
	private Menu s;

	public MenuDaoImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean isRidExists(int rid) {
        try (PreparedStatement checkStmt = con.prepareStatement(CHECK_RID_QUERY)) {
            checkStmt.setInt(1, rid);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public void insertAll(Menu menu) {
		try {
			if (!isRidExists(menu.getRid())) {
                System.out.println("The specified rid does not exist in the restaurant table.");
                return;
            }
		
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setInt(1, menu.getRid());
			pstmt.setString(2, menu.getName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setInt(5, menu.getRating());
			pstmt.setBytes(6, menu.getImage());
			
			res = pstmt.executeUpdate();
			if(res!=0) {
				System.out.println("Data Inserted");
			}
			else 
				System.out.println("Not Inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Menu> fetchAll(int rid) {
		try {
			pstmt = con.prepareStatement(FETCH_SPECIFIC);
			pstmt.setInt(1, rid);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public List<Menu> extractMenuListFromResultSet(ResultSet result) {
		try {
			while(result.next()) {
				menuList.add(new Menu(
						 result.getInt("menu_id"),
	                        result.getInt("restaurant_id"),
	                        result.getString("menu_name"),
	                        result.getString("menu_description"),
	                        result.getInt("price"),
	                        result.getInt("rating"),
	                        result.getString("is_available"),
	                        result.getBytes("image")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}
	@Override
	public List<Menu> getMenu(int restaurant_id ) {
		try {
			pstmt = con.prepareStatement(FETCH_SPECIFIC);
			pstmt.setInt(1, restaurant_id);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	@Override
	public Menu getMenuById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	@Override
	public void update(int id , int price) {
		try {
			pstmt = con.prepareStatement(UPDATE_QUERY);
			pstmt.setInt(2, id);
			pstmt.setInt(1, price);
			res = pstmt.executeUpdate();
			if(res != 0)
				System.out.println("Updated Successful");
			else {
				System.out.println("No record");
			 	System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(int id ) {
		try {
			pstmt = con.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1, id);
			res = pstmt.executeUpdate();
			if(res != 0)
				System.out.println("Row deleted successfully");
			else {
				System.out.println("No Record!");
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//...................................................................................................
	
	public List<Menu> fetchAllNonVeg() {
		try {
			pstmt = con.prepareStatement(FETCH_NON_VEG);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu nonvegById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_NON_VEG_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	//...................................................................................................  
	
	public List<Menu> fetchAllVeg() {
		try {
			pstmt = con.prepareStatement(FETCH_VEG);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu vegById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_VEG_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	
	//...................................................................................................
	public List<Menu> fetchAllBiryaniSpecials() {
		try {
			pstmt = con.prepareStatement(FETCH_BIRYANI_SPECIALS);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu biryaniSpecialsById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_BIRYANI_SPECIALS_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	
	//...................................................................................................
	public List<Menu> fetchAllPizza() {
		try {
			pstmt = con.prepareStatement(FETCH_PIZZA);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu pizzaById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_PIZZA_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	
	//...................................................................................................
	public List<Menu> fetchAllBugger() {
		try {
			pstmt = con.prepareStatement(FETCH_BUGGER);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu burgerById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_BURGER_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	
	//...................................................................................................
	public List<Menu> fetchAllDesserts() {
		try {
			pstmt = con.prepareStatement(FETCH_DESSERTS);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu delightsById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_DELIGHTS_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	
	//...................................................................................................
	public List<Menu> fetchAllDelights() {
		try {
			pstmt = con.prepareStatement(FETCH_DELIGHTS);
			result = pstmt.executeQuery();
			menuList = extractMenuListFromResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	public Menu dessertsById(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_DESSERTS_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    return new Menu(
                            result.getInt("menu_id"),
                            result.getInt("restaurant_id"),
                            result.getString("menu_name"),
                            result.getString("menu_description"),
                            result.getInt("price"),
                            result.getInt("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	public Menu getMenuItemBySource(String source, int menuId) {
	    switch (source.toLowerCase()) {
	        case "nonveg":
	            return nonvegById(menuId);
	        case "veg":
	            return vegById(menuId);
	        case "biryanispecials":
	            return biryaniSpecialsById(menuId);
	        case "pizza":
	            return pizzaById(menuId);
	        case "burger":
	            return burgerById(menuId);
	        case "delights":
	            return delightsById(menuId);
	        case "desserts":
	            return dessertsById(menuId);
	        case "restaurant":
	            return getMenuById(menuId);
	        default:
	            return null; // Invalid source
	    }
	}

	
}

