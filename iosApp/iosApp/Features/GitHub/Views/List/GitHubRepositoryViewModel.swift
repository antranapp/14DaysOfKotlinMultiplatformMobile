//
//  GitHubRepositoryViewModel.swift
//  iosApp
//
//  Created by Binh An Tran on 4/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

final class GitHubRepositoryViewModel: ObservableObject {
    private let _githubViewModel = shared.GitHubViewModel()

    @Published var repos = [Repository]()

    init() {
        _githubViewModel.repos.addObserver { [weak self] repos in
            guard let repos = repos else { return }
            let repositories: [Repository] = repos.compactMap { repo in
                guard let gitHubRepo = repo as? shared.GitHubRepo else { return nil}
                return Repository(
                    id: Int(gitHubRepo.id),
                    repoName: gitHubRepo.name,
                    owner: Owner(
                        avatarImageUrl: URL(string: gitHubRepo.owner.avatar_url),
                        loginName: gitHubRepo.owner.login
                    ),
                    numberOfForks: Int(gitHubRepo.forks_count),
                    numberOfWatchers: Int(gitHubRepo.watchers_count),
                    repoDescription: gitHubRepo.description_,
                    forksUrl: URL(string: gitHubRepo.forks_url),
                    htmlUrl: URL(string: gitHubRepo.html_url),
                    license: License(
                        name: gitHubRepo.license?.name ?? "unknown",
                        licenseUrl: gitHubRepo.license?.url != nil ? URL(string: gitHubRepo.license!.url) : nil
                    )
                )
            }

            DispatchQueue.main.async {
                self?.repos = repositories
            }
        }
    }

    func fetchRepos() {
        _githubViewModel.fetchRepos()
    }
}


