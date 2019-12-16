# Top-Git-Repositories
Android App to display top git repositories using the Android Architectural components - ViewModel, LiveData, Room, Dao.

## Components Used :
1. MainActivity which hosts 2 fragments, one(TopGitRepoListFragment) for displaying the list of top git repos when searched by language and another(TopGitRepoItemFragment) which shares the information of the top-git repo clicked on.

2. RecyclerView using TGRepoAdapter which in turn uses TGRepoItemViewHolder for each of the list items.

3. Model class TGRepo, which also functions for serializing the Json response received and for Database table git_repo_table.

4. ViewModel class which acts as a mediator between the Views(the fragments) for giving in the data to the View from the data repositories.

5. TopGitRepository, which takes in data from TopGitRepoWebService or Room Dao, depending on the response received. It is assumed that onFailure, we are observing a network failure. This can be expanded by adding a check specifically for isNetworkPresent. So, if there is a network failure, instead of the Retrofit API we try and fetch it from the RoomDatabase.
All of the response data once successfully fetched from the Retrofit API, is also inserted to database.

6. TGRepoRoomdbTest, for the unit testing related to the TopGitRepository functionality.

## Enhancements
1. Dagger can be used to inject objects, instead of creating them in the dependents constructor.
2. Network check API can be added. 
3. Test cases for UI Screens can be added.
