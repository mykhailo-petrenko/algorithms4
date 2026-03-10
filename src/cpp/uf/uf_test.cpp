//
// Created by Mykhailo Petrenko on 05/03/2026.
//

#include <iostream>
#include <assert.h>

#include "UF.hpp"

void uf_smoke_test() {
    UF u = UF(10);

    u.add(4, 3);
    u.add(3, 8);
    u.add(6, 5);
    u.add(9, 4);
    u.add(2, 1);

    assert(u.connected(8, 9));

    u.add(5, 0);
    u.add(7, 2);
    u.add(6, 1);

    assert(u.connected(1, 0));
    assert(u.connected(6, 7));
    assert(u.connected(1, 7));
    assert(u.connected(8, 4));

    assert(false==u.connected(0, 9));
    assert(false==u.connected(7, 3));
    assert(false==u.connected(7, 8));
    assert(false==u.connected(2, 3));
}

int main() {
    std::cout << "UF test" << std::endl;
    uf_smoke_test();
    std::cout << "uf_smoke_test passed" << std::endl;

    return 0;
}