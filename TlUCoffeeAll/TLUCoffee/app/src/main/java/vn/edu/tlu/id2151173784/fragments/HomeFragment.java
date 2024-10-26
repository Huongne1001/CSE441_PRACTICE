package vn.edu.tlu.id2151173784.fragments;

import android.os.Bundle;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tlu.id2151173784.api.ApiClient;
import vn.edu.tlu.id2151173784.api.ApiService;
import vn.edu.tlu.id2151173784.adapters.CategoryAdapter;
import vn.edu.tlu.id2151173784.models.Category;
import vn.edu.tlu.id2151173784.R;

public class HomeFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter adapter;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = ApiClient.getClient().create(ApiService.class);
        loadCategories();

        return view;
    }

    private void loadCategories() {
        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();
                    adapter = new CategoryAdapter(categories, HomeFragment.this);
                    categoryRecyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Không có loại món nào", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi khi tải danh sách các loại món: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int categoryId) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ProductFragment.newInstance(categoryId)); // Giả sử bạn đã định nghĩa phương thức newInstance trong ProductFragment
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
