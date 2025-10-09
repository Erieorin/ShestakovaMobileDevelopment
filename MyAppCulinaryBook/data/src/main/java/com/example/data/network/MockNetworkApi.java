package com.example.data.network;

import com.example.domain.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MockNetworkApi {

    public List<Recipe> getMockRecipes() {
        List<Recipe> list = new ArrayList<>();
        list.add(new Recipe(100, "Борщ"));
        list.add(new Recipe(101, "Паста Карбонара"));
        list.add(new Recipe(102, "Картошка фри"));
        return list;
    }
}
