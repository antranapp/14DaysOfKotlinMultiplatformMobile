//
//  GitHubRepositoryView.swift
//  iosApp
//
//  Created by Binh An Tran on 3/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GitHubRepositoryView: View {
    @StateObject private var viewModel = GitHubRepositoryViewModel()

    var body: some View {
        VStack {
            Button(
                action: viewModel.fetchRepos,
                label: {
                    Text("Fetch GitHub Repositories")
                }
            )
        }
    }
}

final class GitHubRepositoryViewModel: ObservableObject {
    private let _githubViewModel = shared.GitHubViewModel()

    init() {
        _githubViewModel.repos.addObserver { [weak self] repos in
            guard let repos = repos else { return }
        }
    }

    func fetchRepos() {
        _githubViewModel.fetchRepos()
    }
}

struct GitHubRepositoryView_Previews: PreviewProvider {
    static var previews: some View {
        GitHubRepositoryView()
    }
}

