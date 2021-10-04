//
//  GitHubRepositoryView.swift
//  iosApp
//
//  Created by Binh An Tran on 3/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GitHubRepositoryListView: View {
    @StateObject private var viewModel = GitHubRepositoryViewModel()

    var body: some View {
        NavigationView {
            List {
                if viewModel.repos.count == 0 {
                    Text("No repository found")
                        .font(.headline)
                        .foregroundColor(Color.red)
                }

                ForEach(viewModel.repos, id: \.id) { repo in
                    NavigationLink(destination: GitHubRepositoryDetailView(repository: repo)) {
                        GitHubRepositoryRowView(repository: repo)
                    }
                }
            }
            .navigationBarTitle("GitHub Repositories")
        }.navigationViewStyle(StackNavigationViewStyle()).onAppear {
            self.viewModel.fetchRepos()
        }
    }
}

struct GitHubRepositoryView_Previews: PreviewProvider {
    static var previews: some View {
        GitHubRepositoryListView()
    }
}

