package com.kasinadhuni.tourismapp.ui.dashboard;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kasinadhuni.tourismapp.R;
public class MapFragment extends Fragment implements OnMapReadyCallback {

    public MapFragment() {
        super(R.layout.fragment_map); // Ensure this layout contains R.id.map_container
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Dynamically add the map fragment into this container
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.map, mapFragment)
                .commit();

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Bundle args = getArguments();

        LatLng location;
        String title;

        if (args != null && args.containsKey("lat") && args.containsKey("lng")) {
            float lat = args.getFloat("lat");
            float lng = args.getFloat("lng");
            title = args.getString("title", "Selected Location");
            location = new LatLng(lat, lng);
        } else {
            location = new LatLng(28.6129, 77.2295); // India Gate
            title = "India Gate";
        }

        googleMap.addMarker(new MarkerOptions().position(location).title(title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
}