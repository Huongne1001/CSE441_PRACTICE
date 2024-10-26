package vn.edu.tlu.id2151173784.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.id2151173784.adapters.CartAdapter;
import vn.edu.tlu.id2151173784.models.CartItem;
import vn.edu.tlu.id2151173784.models.Product;
import vn.edu.tlu.id2151173784.R;


public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyclerView = view.findViewById(R.id.recycleriVewCart);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CartItem> cartItems = getCartItems();
        cartAdapter = new CartAdapter(cartItems, getContext());
        cartRecyclerView.setAdapter(cartAdapter);

        return view;
    }

    private List<CartItem> getCartItems() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String jsonCart = sharedPreferences.getString("cart_items", null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(jsonCart, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        return cartItems;
    }
}
