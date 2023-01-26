const PQ = require('./pq');


test('Basic test', () => {
    const q = new PQ();

    q.enqueue(10);
    q.enqueue(5);
    q.enqueue(7);
    q.enqueue(1);
    q.enqueue(8);
    q.enqueue(3);
    q.enqueue(11);
    q.enqueue(11);

    expect(q.size()).toEqual(8);

    expect(q.dequeue()).toEqual(1);
    expect(q.size()).toEqual(7);

    expect(q.dequeue()).toEqual(3);
    expect(q.size()).toEqual(6);

    expect(q.dequeue()).toEqual(5);
    expect(q.size()).toEqual(5);

    expect(q.dequeue()).toEqual(7);
    expect(q.size()).toEqual(4);

    expect(q.dequeue()).toEqual(8);
    expect(q.size()).toEqual(3);

    expect(q.dequeue()).toEqual(10);
    expect(q.size()).toEqual(2);

    expect(q.dequeue()).toEqual(11);
    expect(q.size()).toEqual(1);

    expect(q.dequeue()).toEqual(11);
    expect(q.size()).toEqual(0);
});
