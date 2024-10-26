package vn.edu.tlu.id2151173784.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.edu.tlu.id2151173784.models.Category;
import vn.edu.tlu.id2151173784.models.Customer;
import vn.edu.tlu.id2151173784.models.LoginRequest;
import vn.edu.tlu.id2151173784.models.LoginResponse;
import vn.edu.tlu.id2151173784.models.Order;
import vn.edu.tlu.id2151173784.models.Product;

public interface ApiService {
//    @GET("products")
//    Call<List<Product>> getProducts();

    @GET("products")
    Call<List<Product>> getProductsByCategory(@Query("category_id") int categoryId);

    @GET("categories")
    Call<List<Category>> getCategories();

    @GET("product/{productId}")
    Call<Product> getProductById(@Path("productId") int productId);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<Customer> register(@Body Customer customer);

    @GET("orders")
    Call<List<Order>> getOrderHistory(@Query("customer_id") int customerId);
}