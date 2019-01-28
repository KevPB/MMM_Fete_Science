package com.example.kev.ftedelascience;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.kev.ftedelascience.R.layout.fragment_events_list;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsList extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Events> events;
    private View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EventsList() {
        // Required empty public constructor
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsList.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsList newInstance(String param1, String param2) {
        EventsList fragment = new EventsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public List<Events> readEvents(){

        List<Events> resultArray = new ArrayList<Events>();
        InputStream inputStream = getResources().openRawResource(R.raw.fresrfetedelascience18);
        CSVFile file = new CSVFile(inputStream);
        String[] myDataset = file.read();
        for(int i = 0; i<myDataset.length; i++){
            String[] eventSplit = myDataset[i].split(",");
            Events event = new Events(i,eventSplit[8],eventSplit[9],eventSplit[6],eventSplit[14]);
            resultArray.add(event);
        }
        return resultArray;


    }


/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // specify an adapter (see also next example)

        InputStream inputStream = getResources().openRawResource(R.raw.fresrfetedelascience18);
        CSVFile file = new CSVFile(inputStream);
        String[] myDataset = file.read();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(fragment_events_list, container, false);
        MainActivity activity = ((MainActivity)  getActivity());
        mRecyclerView = view.findViewById(R.id.events_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        events = readEvents();
        mAdapter = new MyAdapter(events);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
