package com.tulik15b.topgitrepositories;

import android.app.Application;
import android.content.Context;
import android.content.pm.InstrumentationInfo;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.tulik15b.topgitrepositories.model.TGRepo;
import com.tulik15b.topgitrepositories.repository.ITGRepoDao;
import com.tulik15b.topgitrepositories.repository.TGRepoRoomDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TGRepoRoomdbTest {

    TGRepoRoomDataBase roomDataBase;
    ITGRepoDao dao;

    @Before
    public void initDb(){

        Context context = ApplicationProvider.getApplicationContext();
        roomDataBase = Room.inMemoryDatabaseBuilder(context, TGRepoRoomDataBase.class).allowMainThreadQueries().build();
         dao = roomDataBase.gitDao();
    }

    @After
    public void closeDb(){
        roomDataBase.close();
    }

    @Test
    public void fetchAllRepositoriesTest(){
        TGRepo repo = new TGRepo();
        repo.name = "TestName";
        repo.language = "Java";
        repo.username = "UserName";
        repo.avatarUrl = "url";
        repo.url = "url";
        repo.id = 1;
        dao.insertRecords(repo);

        List<TGRepo> list = dao.fetchAllRepositories("Java");
        assert(!list.isEmpty());
    }

    @Test
    public void getSearchReposTest(){
        TGRepo repo = new TGRepo();
        repo.name = "TestName";
        repo.language = "Java";
        repo.username = "UserName";
        repo.avatarUrl = "url";
        repo.url = "url";
        repo.id = 2;
        dao.insertRecords(repo);

        assertNotNull(dao.fetchSearchedRepos("TestName"));
    }

    @Test
    public void getRepoFromRepoIdTest(){
        TGRepo repo = new TGRepo();
        repo.name = "TestName";
        repo.language = "Java";
        repo.username = "UserName";
        repo.avatarUrl = "url";
        repo.url = "url";
        repo.id = 2;
        dao.insertRecords(repo);

        assertNotNull(dao.fetchRepoFromName("TestName"));
    }


}
