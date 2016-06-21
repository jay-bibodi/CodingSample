package com.pratice.modernartui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity
{
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;
    FragmentFive fragmentFive;

    SeekBar seekBar=null;
    private DialogFragment dialogFragment;

    static private final String URL = "http://www.google.com";
    static private final String CHOOSER_TEXT = "Load " + URL + " with:";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar) findViewById(R.id.seekBarId);
        fragmentOne=(FragmentOne) getFragmentManager().findFragmentById(R.id.fragmentOne);
        fragmentTwo=(FragmentTwo) getFragmentManager().findFragmentById(R.id.fragmentTwo);
        fragmentThree=(FragmentThree) getFragmentManager().findFragmentById(R.id.fragmentThree);
        fragmentFour=(FragmentFour) getFragmentManager().findFragmentById(R.id.fragmentFour);
        fragmentFive=(FragmentFive)getFragmentManager().findFragmentById(R.id.fragmentFive);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                {
                    fragmentOne.setTextViewColor(progress);
                    fragmentTwo.setTextViewColor(progress);
                    fragmentThree.setTextViewColor(progress);
                    fragmentFour.setTextViewColor(progress);
                    fragmentFive.setTextViewColor(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.top_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.menu_one_id)
        {
            showDialogFragment(0);
        }
        return super.onOptionsItemSelected(item);
    }

    void showDialogFragment(int dialogID)
    {
        if(dialogID==0)
        {
            dialogFragment = AlertDialogFragment.newInstance();
            dialogFragment.show(getFragmentManager(), "Alert");
        }
    }

// Class that creates the AlertDialog
public static class AlertDialogFragment extends DialogFragment {

    public static AlertDialogFragment newInstance() {
        return new AlertDialogFragment();
    }

    // Build AlertDialog using AlertDialog.Builder
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity()).setMessage("\tDo you want to search more?\n\t Click below to learn more")

                // User cannot dismiss dialog by hitting back button
                .setCancelable(true)

                // Set up No Button
                .setNegativeButton("Not Now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ((MainActivity) getActivity())
                                        .continueShutdown(false);
                            }
                        })

                // Set up Yes Button
                .setPositiveButton("Visit Google Search",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    final DialogInterface dialog, int id) {
                                ((MainActivity) getActivity())
                                        .continueShutdown(true);
                            }
                        }).create();
    }
}

    // Abort or complete ShutDown based on value of shouldContinue
    private void continueShutdown(boolean shouldContinue) {
        if (shouldContinue) {
            try {
                // Pretend to do something before
                // shutting down
                Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(baseIntent);
                //Intent chooserIntent = Intent.createChooser(baseIntent, CHOOSER_TEXT);
                /*if (baseIntent.resolveActivity(getPackageManager()) != null) {
                    Thread.sleep(1000);

                }*/
            } catch (Exception e) {
                //Log.i(TAG, e.toString());
            } finally {
                finish();
            }
        } else {

            // Abort ShutDown and dismiss dialog
            dialogFragment.dismiss();
        }
    }
}