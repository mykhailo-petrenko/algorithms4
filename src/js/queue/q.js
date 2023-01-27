class Queue {
    constructor() {
        this.array = [];
    }

    size() {
        return this.array.length;
    }

    enqueue(value) {
        this.array.push(value);
    }

    dequeue() {
        return this.array.shift();
    }
}

module.exports = Queue;