package vn.edu.tlu.id2151173784.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import retrofit2.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tlu.id2151173784.R;
import vn.edu.tlu.id2151173784.api.ApiClient;
import vn.edu.tlu.id2151173784.api.ApiService;
import vn.edu.tlu.id2151173784.fragments.CartFragment;
import vn.edu.tlu.id2151173784.fragments.HomeFragment;
import vn.edu.tlu.id2151173784.fragments.OrderHistoryFragment;
import vn.edu.tlu.id2151173784.fragments.ProductFragment;
import vn.edu.tlu.id2151173784.models.Category;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ApiService apiService;
    private Map<Integer, Integer> categoryToMenuId = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, new HomeFragment());
            transaction.commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        apiService = ApiClient.getClient().create(ApiService.class);
        loadCategories();
    }

    private void loadCategories() {
        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setupNavigationMenu(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi khi tải danh mục", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNavigationMenu(List<Category> categories) {
        Menu menu = navigationView.getMenu();
        menu.clear();

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.contains("user_token");

        for (Category category : categories) {
            MenuItem item = menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, category.getName());
            categoryToMenuId.put(item.getItemId(), category.getId());
        }

        MenuItem divider = menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, " ");
        divider.setEnabled(false);
        divider.setTitle("------------------------------------------------------------");

        if (isLoggedIn) {
            menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Giỏ hàng");
            menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Lịch sử mua hàng");
            menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Cài đặt");
            menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Đăng xuất");
        } else {
            menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Đăng nhập");
            menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Đăng ký");
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Integer categoryId = categoryToMenuId.get(item.getItemId());

        if (categoryId != null) {
            getSupportActionBar().setTitle(item.getTitle());
            loadProductFragment(categoryId);
        } else {
            switch (item.getTitle().toString()) {
                case "Giỏ hàng":
                    loadCartFragment();
                    break;
                case "Lịch sử mua hàng":
                    loadOrderHistoryFragment();
                    break;
                case "Cài đặt":
                    break;
                case "Đăng xuất":
                    logout();
                    break;
                case "Đăng nhập":
                    Intent loginIntent = new Intent(this, LoginActivity.class);
                    startActivity(loginIntent);
                    break;
                case "Đăng ký":
                    Intent registerIntent = new Intent(this, RegisterActivity.class);
                    startActivity(registerIntent);
                    break;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadProductFragment(int categoryId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ProductFragment.newInstance(categoryId));
        transaction.commit();
    }

    private void loadCartFragment() {
        CartFragment cartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, cartFragment)
                .addToBackStack(null)
                .commit();
    }

    private void loadOrderHistoryFragment() {
        OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, orderHistoryFragment)
                .addToBackStack(null)
                .commit();
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("user_token");
        editor.remove("customerId");
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack để ngăn back lại màn hình chính
        startActivity(intent);

        Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
    }

}
