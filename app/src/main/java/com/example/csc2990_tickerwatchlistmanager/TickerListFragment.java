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

import com.example.csc2990_tickerwatchlistmanager.R;

public class TickerListFragment extends Fragment {

    String[] tickers = {"NEE", "AAPL", "DIS"}; // start with 3 defaults
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);

        listView = view.findViewById(R.id.ticker_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                tickers
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ticker = tickers[position];
                InfoWebFragment webFragment = (InfoWebFragment)
                        getActivity().getSupportFragmentManager()
                                .findFragmentById(R.id.infoWebFragment);
                webFragment.loadUrlForTicker(ticker);
            }
        });

        return view;
    }
}
