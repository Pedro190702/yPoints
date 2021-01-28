package com.ystoreplugins.ypoints.database;

import java.util.ArrayList;
import java.util.List;

import com.ystoreplugins.ypoints.database.datamanager.CachedDataManager;
import com.ystoreplugins.ypoints.database.datasource.AbstractDataSource;
import com.ystoreplugins.ypoints.models.PlayerPoints;

public class MainDataManager {

    public final CachedDataManager<String, PlayerPoints> USERS;

    @SuppressWarnings("rawtypes")
	private List<CachedDataManager> daos;

    public MainDataManager(AbstractDataSource abstractDataSource) {
        this.daos = new ArrayList<>();
        daos.add(USERS = new CachedDataManager<>(abstractDataSource, "ypoints", PlayerPoints.class));

    }

    public int saveCached(boolean async) {
        daos.forEach(dao -> dao.saveCached(async));
        return daos.stream().mapToInt(dao -> dao.getCached().size()).sum();
    }
}