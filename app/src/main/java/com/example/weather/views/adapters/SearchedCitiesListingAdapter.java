package com.example.weather.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Models.CurrentWeatherDto;
import com.example.weather.databinding.SearchedLocationsListItemBinding;

import java.util.List;

public class SearchedCitiesListingAdapter extends RecyclerView.Adapter<SearchedCitiesListingAdapter.ViewHolder> {

    private List<CurrentWeatherDto> itemsList;


    public SearchedCitiesListingAdapter(List<CurrentWeatherDto> itemsList) {
        this.itemsList = itemsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchedLocationsListItemBinding binding = SearchedLocationsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrentWeatherDto item = itemsList.get(position);


        StringBuilder name = new StringBuilder();

        name.append(item.getCityName()).append(", ").append(item.getSys().getCountryShortName());
        holder.binding.tvCityName.setText(item.getCityName());
        StringBuilder temperatureStringBuilder = new StringBuilder();
        temperatureStringBuilder.append(item.getTemperatureStats().getTemperature()).append("°");
        holder.binding.tvTemperature.setText(temperatureStringBuilder);
        StringBuilder minMaxTemp = new StringBuilder();

        minMaxTemp.append("H:").append(item.getTemperatureStats().getMaxTemperature()).append("°").append("  ");
        minMaxTemp.append("L:").append(item.getTemperatureStats().getMinTemperature()).append("°");


        holder.binding.tvLowHighTemperature.setText(minMaxTemp);

        StringBuilder weatherDesc = new StringBuilder(item.getWeatherDetails()[0].getDescription());
        holder.binding.tvWeatherDescription.setText(weatherDesc);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SearchedLocationsListItemBinding binding;

        public ViewHolder(@NonNull SearchedLocationsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public SearchedLocationsListItemBinding getBinding() {
            return binding;
        }
    }
}
