//
//  GitHubRepositoryRowView.swift
//  iosApp
//
//  Created by Binh An Tran on 4/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

import SwiftUI

struct GitHubRepositoryRowView: View {

    let repository: Repository

    var body: some View {
        HStack {
            RemoteImageContainer(url: repository.owner!.avatarImageUrl)

            VStack(alignment: .leading, spacing: 5) {
                Text(repository.repoName)
                    .font(.headline)

                Text("Forks: \(repository.numberOfForks ?? 0)")
                    .font(.subheadline)
                    .foregroundColor(Color.gray)

                Text("Watchers: \(repository.numberOfWatchers ?? 0)")
                    .font(.subheadline)
                    .foregroundColor(Color.gray)
            }

            Spacer()
        }
    }
}
