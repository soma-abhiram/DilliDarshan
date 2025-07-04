package com.kasinadhuni.tourismapp.ui.attractions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kasinadhuni.tourismapp.R;

import java.util.ArrayList;
import java.util.List;

public class AttractionsFragment extends Fragment {

    RecyclerView recyclerView;
    List<Attraction> attractionList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_attractions, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_attractions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        attractionList = new ArrayList<>();
        attractionList.add(new Attraction("Red Fort", "Historic fort in Old Delhi", R.drawable.red_fort, 28.6562, 77.2410));
        attractionList.add(new Attraction("India Gate", "War memorial in New Delhi", R.drawable.india_gate, 28.6129, 77.2295));
        attractionList.add(new Attraction("Qutub Minar", "Tallest brick minaret in the world", R.drawable.qutub_minar, 28.5244, 77.1855));
        attractionList.add(new Attraction("Lotus Temple", "Baháʼí House of Worship shaped like a lotus flower", R.drawable.lotus_temple, 28.5535, 77.2588));
        attractionList.add(new Attraction("Akshardham Temple", "Hindu temple known for its grand architecture", R.drawable.akshardham, 28.6127, 77.2773));
        attractionList.add(new Attraction("Humayun’s Tomb", "Mughal emperor’s tomb with Persian architecture", R.drawable.humayuns_tomb, 28.5933, 77.2507));
        attractionList.add(new Attraction("Jama Masjid", "One of the largest mosques in India", R.drawable.jama_masjid, 28.6507, 77.2334));
        attractionList.add(new Attraction("Rashtrapati Bhavan", "Official residence of the President of India", R.drawable.rashtrapati_bhavan_, 28.6143, 77.1995));
        attractionList.add(new Attraction("Chandni Chowk", "Bustling market with street food and shopping", R.drawable.chandni_chowk, 28.6565, 77.2303));

        AttractionAdapter adapter = new AttractionAdapter(attractionList, attraction -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", attraction.title);
            bundle.putFloat("lat", (float) attraction.latitude);
            bundle.putFloat("lng", (float) attraction.longitude);

            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
           // ✅ Use correct ID
            // ✅ This is the dedicated screen, not the tab
            navController.navigate(R.id.mapFragment, bundle);
        });

        recyclerView.setAdapter(adapter);
        return view;
    }
}