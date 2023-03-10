package com.example.weather.views.activities;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather.Base.BaseActivity;
import com.example.weather.databinding.AddNewCityActivityBinding;
import com.example.weather.views.ViewModel.AddNewCityViewModel;

/**
 * In this class search city name to fetch its current weather details and
 * list already searched cities along with its weather details.
 */
public class AddNewCityActivity extends BaseActivity {

    private AddNewCityActivityBinding binding;
    private AddNewCityViewModel addNewCityViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddNewCityActivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        addNewCityViewModel = new ViewModelProvider(this).get(AddNewCityViewModel.class);
        addActions();
    }

    /**
     * To all action that can be performed on views
     */
    private void addActions() {
        binding.tvSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String searchText = binding.tvSearch.getText().toString();
                if (!searchText.isEmpty())
                    addNewCityViewModel.searchCityAndWeatherDetails(searchText);
                return true;
            }
            return false;
        });
    }
}
