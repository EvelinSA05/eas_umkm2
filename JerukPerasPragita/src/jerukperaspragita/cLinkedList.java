package jerukperaspragita;

public class cLinkedList {
    cSimpul head, tail;
    int jumlah;

    public cLinkedList() {
        head = tail = null;
        jumlah = 0;
    }
    
    public void enqueue(cSimpul baru) {
        if (head == null) {
            head = tail = baru;
        } else {
            tail.next = baru;
            tail = baru;
        }
        jumlah++;
        System.out.println("Transaksi ["+baru.data.getKode()+"] berhasil ditambahkan ke antrean...");
    }
    
    public cSimpul getHead() {
        return head;
    }
}