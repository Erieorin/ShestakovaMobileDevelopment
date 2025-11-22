package com.example.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.FragmentResultListener;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener(
                "requestKey", this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        String text = bundle.getString("key");
                        Log.d(BottomSheetFragment.class.getSimpleName(), "Get text " + text);
                    }
                });
    }
}