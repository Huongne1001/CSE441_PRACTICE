package vn.edu.tlu.id2151173784.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tlu.id2151173784.api.ApiClient;
import vn.edu.tlu.id2151173784.api.ApiService;
import vn.edu.tlu.id2151173784.models.CartItem;
import vn.edu.tlu.id2151173784.models.Product;
import vn.edu.tlu.id2151173784.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "product_id";
    private ApiService apiService;
    private TextView productNameTextView;
    private TextView productDescriptionTextView;
    private TextView productPriceTextView;
    private ImageView productImageView;
    private Button addToCartButton;
    private Product currentProduct;

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        productNameTextView = view.findViewById(R.id.product_name_text_view);
        productDescriptionTextView = view.findViewById(R.id.product_description_text_view);
        productPriceTextView = view.findViewById(R.id.product_price_text_view);
        productImageView = view.findViewById(R.id.product_image_view);
        addToCartButton = view.findViewById(R.id.addToCartButton);


        apiService = ApiClient.getClient().create(ApiService.class);

        if (getArguments() != null) {
            int productId = getArguments().getInt(ARG_PRODUCT_ID);
            fetchProductDetails(productId);
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentProduct != null) {
                    addToCart(currentProduct, 1);
                } else {
                    Toast.makeText(getContext(), "Sản phẩm chưa được tải về", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }

    private void fetchProductDetails(int productId) {
        apiService.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    currentProduct = product;

                    productNameTextView.setText(product.getName());
                    productDescriptionTextView.setText(product.getDescription());
                    productPriceTextView.setText(String.format("Giá: %d VND", product.getPrice()));
                    Glide.with(getContext()).load("http://10.0.2.2:8000/images/" + product.getImage()).into(productImageView);
                } else {
                    Toast.makeText(getContext(), "Không tìm thấy sản phẩm." , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi khi tải sản phẩm: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart(Product product, int quantity) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String jsonCart = sharedPreferences.getString("cart_items", null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(jsonCart, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);

        String updatedJsonCart = gson.toJson(cartItems);
        editor.putString("cart_items", updatedJsonCart);
        editor.apply();

        Toast.makeText(getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}
