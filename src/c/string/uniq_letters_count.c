//
// Created by Mykhailo Petrenko on 21/12/2022.
//
#include <stdlib.h>
#include <stdio.h>

const int ABC_LENGTH = 26;

struct LettersStat {
    int length;
    int uniqSymbolsCount;
    int *symbolsStatistic;
};

void addSymbol(struct LettersStat * stat, char s) {
    stat->symbolsStatistic[s]++;

    if (stat->symbolsStatistic[s] == 1) {
        stat->uniqSymbolsCount++;
    }

    stat->length++;
}

void removeSymbol(struct LettersStat * stat, char s) {
    stat->symbolsStatistic[s]--;
    if (stat->symbolsStatistic[s] == 0) {
        stat->uniqSymbolsCount--;
    }

    stat->length--;
}

void initStat(struct LettersStat * stat) {
    stat->length = 0;
    stat->uniqSymbolsCount = 0;

    stat->symbolsStatistic = malloc(sizeof(int) * ABC_LENGTH);
    for (int i=0; i<ABC_LENGTH; i++) {
        stat->symbolsStatistic[i] = 0;
    }
}

int main(int argc, char **argv) {

    struct LettersStat stat;

    initStat(&stat);

    char * s1 = "aababcaabd";
    char * tail = s1;

    while (*s1 != '\0') {
        addSymbol(&stat, *s1);
        s1++;
    }

    printf("%d / %d", stat.uniqSymbolsCount, stat.length);

    while (*tail != '\0') {
        removeSymbol(&stat, *tail);
        tail++;
    }



    return 0;
}