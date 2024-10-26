package vn.edu.tlu.id2151173784.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tlu.id2151173784.api.ApiClient;
import vn.edu.tlu.id2151173784.api.ApiService;
import vn.edu.tlu.id2151173784.R;
import vn.edu.tlu.id2151173784.adapters.ProductAdapter;
import vn.edu.tlu.id2151173784.models.Product;

public class ProductFragment extends Fragment implements ProductAdapter.OnProductClickListener {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ApiService apiService;
    private int categoryId;

    public static ProductFragment newInstance(int categoryId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt("category_id", categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getInt("category_id");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productAdapter = new ProductAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(productAdapter);

        apiService = ApiClient.getClient().create(ApiService.class);
        loadProducts();

        return view;
    }

    private void loadProducts() {
        apiService.getProductsByCategory(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productAdapter.setProducts(response.body());
                } else {
                    Toast.makeText(getContext(), "Không có sản phẩm nào", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi khi tải dữ liệu: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onProductClick(int productId) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ProductDetailFragment.newInstance(productId));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
