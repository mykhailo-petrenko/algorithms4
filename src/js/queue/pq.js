// Pririty Q
class PQ {
    constructor(compare = null) {
        this.array = [];
        this.length = 0;
        this.compare = compare;

        // Use Default comparator if not defined
        if (!this.compare) {
            this.compare = (a, b) => {
                return a - b;
            };
        }
    }

    size() {
        return this.length;
    }

    enqueue(value) {
        this.length++;
        let i = this.length;
        let parent;
        let diff;

        this.array[i] = value;

        while (i > 1) {
            parent = Math.floor(i/2);

            diff = this.compare(this.array[i], this.array[parent]);

            if (diff < 0) {
                this._swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    dequeue() {
        this.array[0] = this.array[1];

        this.array[1] = this.array[this.length];
        this.length--;

        let i = 1;
        let left, right, next;

        while(i < this.length) {
            left = i * 2;
            right = i * 2 + 1;

            if (this.compare(this.array[left],this.array[right]) > 0) {
                left++;
                right--;
            }

            if(this.compare(this.array[i],this.array[left]) > 0) {
                next = left;
            } else if(this.compare(this.array[i],this.array[right]) > 0) {
                next = right;
            } else {
                break;
            }

            this._swap(i, next);
            i = next;
        }

        return this.array[0];
    }

    _swap(i, j) {
        const tmp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = tmp;
    }
}

module.exports = PQ;