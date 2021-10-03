import SwiftUI
import shared

struct HomeView: View {
    var body: some View {
        TabView {
            CounterView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }

            GitHubRepositoryView()
                .tabItem {
                    Image(systemName: "bookmark.circle.fill")
                    Text("Bookmark")
                }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
