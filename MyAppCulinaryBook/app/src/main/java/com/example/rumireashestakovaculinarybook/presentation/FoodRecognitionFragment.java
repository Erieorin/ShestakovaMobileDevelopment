package com.example.rumireashestakovaculinarybook.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rumireashestakovaculinarybook.R;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.vision.classifier.Classifications;
import org.tensorflow.lite.task.vision.classifier.ImageClassifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FoodRecognitionFragment extends Fragment {

    private static final int PICK_IMAGE = 100;

    private ImageView ivFood;
    private TextView tvResult;
    private Button btnLoadImage;

    private ImageClassifier classifier;
    private List<String> labels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_recognition, container, false);

        ivFood = view.findViewById(R.id.ivFood);
        tvResult = view.findViewById(R.id.tvResult);
        btnLoadImage = view.findViewById(R.id.btnLoadImage);

        try {
            labels = loadLabels("labels.csv");
        } catch (IOException e) {
            e.printStackTrace();
            labels = new ArrayList<>();
        }

        try {
            classifier = ImageClassifier.createFromFile(getContext(), "food_model.tflite");
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnLoadImage.setOnClickListener(v -> openGallery());

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                InputStream is = getContext().getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                ivFood.setImageBitmap(bitmap);
                recognizeFood(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> loadLabels(String fileName) throws IOException {
        List<String> labelList = new ArrayList<>();
        InputStream is = getContext().getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String[] parts = line.split(",", 2);
            if (parts.length == 2) {
                String name = parts[1].trim();
                labelList.add(name);
            }
        }
        reader.close();


        return labelList;
    }

    private void recognizeFood(Bitmap bitmap) {
        if (classifier == null || labels.isEmpty()) return;

        TensorImage image = TensorImage.fromBitmap(bitmap);
        List<Classifications> results = classifier.classify(image);

        if (!results.isEmpty()) {
            List<Category> categories = results.get(0).getCategories();
            StringBuilder sb = new StringBuilder("Распознано:\n");

            for (int i = 0; i < Math.min(3, categories.size()); i++) {
                Category c = categories.get(i);
                String label = c.getLabel();

                if (label == null || label.startsWith("/m/") || label.isEmpty()) {
                    int idx = c.getIndex();
                    Log.d("IndexFood", String.valueOf(idx));
                    if (idx >= 0 && idx < labels.size()) {
                        label = labels.get(idx);
                    } else {
                        label = "Unknown";
                    }
                }

                sb.append(String.format("%s: %.1f%%\n", label, c.getScore() * 100));
            }

            tvResult.setText(sb.toString());
        }
    }
}
