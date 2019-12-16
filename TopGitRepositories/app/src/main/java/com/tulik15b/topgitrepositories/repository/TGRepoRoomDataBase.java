package com.tulik15b.topgitrepositories.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tulik15b.topgitrepositories.model.TGRepo;

@Database(entities = {TGRepo.class}, version = 1, exportSchema = false)
public abstract class TGRepoRoomDataBase extends RoomDatabase {

    public abstract ITGRepoDao gitDao();

    private static volatile TGRepoRoomDataBase INSTANCE;

    public static TGRepoRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TGRepoRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TGRepoRoomDataBase.class, "git_trending_database")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}

