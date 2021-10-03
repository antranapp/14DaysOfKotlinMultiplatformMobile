//
//  CounterView.swift
//  iosApp
//
//  Created by Binh An Tran on 3/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct CounterView: View {
    let greet = Greeting().greeting()
    @StateObject private var viewModel = CounterViewModel()

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
        }
    }
}

final class CounterViewModel: ObservableObject {
    private let _counterViewModel = shared.CounterViewModel()

    @Published var count: Int = 0

    init() {
        _counterViewModel.count.addObserver { [weak self] value in
            guard let value = value else { return }
            self?.count = Int(truncating: value)
        }
    }

    func increase() {
        _counterViewModel.increase()
    }

    func decrease() {
        _counterViewModel.decrease()
    }
}

struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView()
    }
}
