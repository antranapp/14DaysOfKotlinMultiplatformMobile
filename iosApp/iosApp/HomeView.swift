import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()
    @StateObject private var viewModel = HomeViewModel()

    var body: some View {
        VStack {
            Text(greet)

            HStack {
                Button(
                    action: viewModel.increase,
                    label: {
                        Text("+")
                    }
                )
                .padding()

                Text("Count: \(viewModel.count)")

                Button(
                    action: viewModel.decrease,
                    label: {
                        Text("-")
                    }
                )
                .padding()
            }

            Button(
                action: viewModel.fetchRepos,
                label: {
                    Text("Fetch GitHub Repositories")
                }
            )
        }
    }
}

final class HomeViewModel: ObservableObject {
    private let _homeViewModel = shared.CounterViewModel()

    private let _githubViewModel = shared.GitHubViewModel()

    @Published var count: Int = 0

    init() {
        _homeViewModel.count.addObserver { [weak self] value in
            guard let value = value else { return }
            self?.count = Int(truncating: value)
        }

        _githubViewModel.repos.addObserver { [weak self] repos in
            guard let repos = repos else { return }
        }
    }

    func increase() {
        _homeViewModel.increase()
    }

    func decrease() {
        _homeViewModel.decrease()
    }

    func fetchRepos() {
        _githubViewModel.fetchRepos()
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
