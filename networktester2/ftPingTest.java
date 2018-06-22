package com.harmonyideas.nettester2;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ftPingTest extends android.support.v4.app.Fragment {

    private String responseText = "";
    TextView status;
    private static String KEY_TEXT_VALUE = "pingInput";
    private static String KEY_NWTEST_VALUE = "pingTest";


    //this override the implemented method from AsyncResponse

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            status.setText(savedInstanceState.getString(KEY_TEXT_VALUE));

        }
        setRetainInstance(true);
    }


    @Override
    public void onSaveInstanceState(final Bundle outState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_TEXT_VALUE, status.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Message Handler for Runnable Thread

        View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);

        final Button button = (Button) rootView.findViewById(R.id.pingBtn);
        final EditText input = (EditText) rootView.findViewById(R.id.pingInput);
        status = (TextView) rootView.findViewById(R.id.pingView);

        if (savedInstanceState != null) {
            status.setText(savedInstanceState.getString(KEY_TEXT_VALUE));
        }

        status.setMovementMethod(new ScrollingMovementMethod());
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    new SendPost(new SendPost.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            output = output.replace("\\n", "\r\n");
                            output = output.replace("\"", "\n");
                            status.setText(output);
                        }
                    }, getActivity(), KEY_NWTEST_VALUE).execute(input.getText().toString());

                } catch (final Exception e) {
                    responseText = e.toString();
                }
                status.setText(responseText);
            }
        });
        return rootView;
    }
}
