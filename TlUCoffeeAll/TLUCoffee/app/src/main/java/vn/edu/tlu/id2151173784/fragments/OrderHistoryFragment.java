package vn.edu.tlu.id2151173784.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.tlu.id2151173784.R;
import vn.edu.tlu.id2151173784.adapters.OrderHistoryAdapter;
import vn.edu.tlu.id2151173784.api.ApiClient;
import vn.edu.tlu.id2151173784.api.ApiService;
import vn.edu.tlu.id2151173784.models.Order;

public class OrderHistoryFragment extends Fragment {
    private RecyclerView recyclerViewOrders;
    private OrderHistoryAdapter adapter;
    private List<Order> orders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        recyclerViewOrders = view.findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchOrderHistory();

        return view;
    }

    private void fetchOrderHistory() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int customerId = sharedPreferences.getInt("customer_id", -1);

        Log.d("Customer ID: ", String.valueOf(customerId));

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getOrderHistory(customerId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    orders = response.body();
                    adapter = new OrderHistoryAdapter(orders);
                    recyclerViewOrders.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }
}
