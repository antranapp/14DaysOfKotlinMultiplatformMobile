//
// Copyright © 2021 An Tran. All rights reserved.
//

import SwiftUI

struct GitHubRepositoryDetailView: View {
    var repository: Repository
    
    private let heightButtons: CGFloat = 45
    
    var body: some View {
        VStack(spacing: 32) {
            RemoteImageContainer(url: repository.owner?.avatarImageUrl, width: 100, height: 100)
            
            Text(repository.repoName)
                .bold()
                .font(.title)
            
            VStack(spacing: 16) {
                if repository.repoDescription != nil {
                    Text(repository.repoDescription!)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
                
                if repository.license?.name != nil {
                    Text("License: \(repository.license!.name)")
                }
            }

            Spacer()
        }
        .padding()
    }
}
