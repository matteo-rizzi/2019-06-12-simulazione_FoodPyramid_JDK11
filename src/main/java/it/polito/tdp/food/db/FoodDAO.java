package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.IngredienteCibi;
import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Condiment;

public class FoodDAO {

	public List<Food> listAllFood(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_id"),
							res.getInt("food_code"),
							res.getString("display_name"), 
							res.getInt("portion_default"), 
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"),
							res.getDouble("calories")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}
	
	public void listAllCondiment(Map<Integer, Condiment> idMap){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					if(!idMap.containsKey(res.getInt("condiment_code"))) {
						Condiment condiment = new Condiment(res.getInt("condiment_code"),
								res.getString("display_name"), 
								res.getString("condiment_portion_size"), 
								res.getDouble("condiment_calories")
								);
						idMap.put(res.getInt("condiment_code"), condiment);
					}
					
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Condiment> getCondimentsByCalories(double calorie) {
		String sql = "SELECT * FROM condiment WHERE condiment_calories < ?";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setDouble(1, calorie);
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"), 
							res.getString("condiment_portion_size"), 
							res.getDouble("condiment_calories")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Adiacenza> getAdiacenze(double calorie, Map<Integer, Condiment> idMap) {
		String sql = "SELECT fc1.condiment_code AS primo, fc2.condiment_code AS secondo, COUNT(DISTINCT fc1.food_code) AS peso FROM food_condiment AS fc1, food_condiment AS fc2, condiment AS c1, condiment AS c2 WHERE fc1.condiment_code < fc2.condiment_code AND fc1.food_code = fc2.food_code AND fc1.condiment_code = c1.condiment_code AND fc2.condiment_code = c2.condiment_code AND c1.condiment_calories < ? AND c2.condiment_calories < ? GROUP BY fc1.condiment_code, fc2.condiment_code";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setDouble(1, calorie);
			st.setDouble(2, calorie);
			
			List<Adiacenza> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Adiacenza a = new Adiacenza(idMap.get(res.getInt("primo")), idMap.get(res.getInt("secondo")), res.getInt("peso"));
					list.add(a);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<IngredienteCibi> getIngredientiCibi(Map<Integer, Condiment> idMap, double calorie) {
		String sql = "SELECT food_condiment.condiment_code AS ingrediente, condiment.condiment_calories AS calorie, COUNT(DISTINCT food_code) AS numeroCibi FROM food_condiment, condiment WHERE food_condiment.condiment_code = condiment.condiment_code AND condiment.condiment_calories < ? GROUP BY food_condiment.condiment_code";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setDouble(1, calorie);
			
			List<IngredienteCibi> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					IngredienteCibi ic = new IngredienteCibi(idMap.get(res.getInt("ingrediente")), res.getDouble("calorie"), res.getInt("numeroCibi"));
					list.add(ic);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
}

