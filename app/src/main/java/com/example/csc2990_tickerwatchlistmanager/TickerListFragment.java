package com.example.csc2990_tickerwatchlistmanager;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TickerListFragment extends Fragment {

    private static ArrayList<String> tickers;
    private ArrayAdapter<String> adapter;
    private ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);


        if (tickers == null) {
            tickers = new ArrayList<>(Arrays.asList("NEE", "AAPL", "DIS"));
        }

        listView = view.findViewById(R.id.ticker_list);

        adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                tickers
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                String ticker = tickers.get(position);
                InfoWebFragment webFragment = (InfoWebFragment)
                        getActivity().getSupportFragmentManager()
                                .findFragmentById(R.id.infoWebFragment);
                if (webFragment != null) {
                    webFragment.loadUrlForTicker(ticker);
                }
            }
        });


        return view;
    }
    private int nextIndex = 0;

    //Ec: round robin
    public void addTicker(String ticker) {
        if (tickers.size() < 6) {
            tickers.add(ticker);
            if (tickers.size() == 6) {
                nextIndex = 0;
            }
        } else {
            tickers.set(nextIndex, ticker);
            nextIndex = (nextIndex + 1) % 6;
        }
        adapter.notifyDataSetChanged();

    }

}
