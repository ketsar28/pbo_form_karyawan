import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Program Form Input Data Karyawan
 * Dengan fitur penyimpanan data ke ArrayList dan tampilan JTable
 *
 * Tugas 2 - Pemrograman Berorientasi Objek
 *
 * @author Muhammad Ketsar Ali Abi Wahid - 230401070204
 */
public class TUGAS2_PBO_230401070204_MUHAMMAD_KETSAR_ALI_ABI_WAHID extends JFrame {

    // ==================== MODEL CLASS ====================
    // Class untuk menyimpan data karyawan (konsep OOP: Encapsulation)
    static class Karyawan {
        private String nik;
        private String nama;
        private String alamat;
        private String tempatLahir;
        private String tanggalLahir;
        private String jenisKelamin;
        private String telepon;
        private String statusPernikahan;
        private String alamat1;
        private String alamat2;

        // Constructor
        public Karyawan(String nik, String nama, String alamat, String tempatLahir,
                        String tanggalLahir, String jenisKelamin, String telepon,
                        String statusPernikahan, String alamat1, String alamat2) {
            this.nik = nik;
            this.nama = nama;
            this.alamat = alamat;
            this.tempatLahir = tempatLahir;
            this.tanggalLahir = tanggalLahir;
            this.jenisKelamin = jenisKelamin;
            this.telepon = telepon;
            this.statusPernikahan = statusPernikahan;
            this.alamat1 = alamat1;
            this.alamat2 = alamat2;
        }

        // Getter methods
        public String getNik() {
            return nik;
        }

        public String getNama() {
            return nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getTempatLahir() {
            return tempatLahir;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public String getTelepon() {
            return telepon;
        }

        public String getStatusPernikahan() {
            return statusPernikahan;
        }

        public String getAlamat1() {
            return alamat1;
        }

        public String getAlamat2() {
            return alamat2;
        }

        // Method untuk konversi ke array (untuk JTable)
        public Object[] toArray() {
            return new Object[] { nik, nama, alamat, tempatLahir + ", " + tanggalLahir,
                    jenisKelamin, telepon, statusPernikahan, alamat1, alamat2 };
        }
    }

    // ==================== ATRIBUT ====================
    // Komponen form input
    private JTextField txtNIK, txtNama, txtTempatLahir, txtTelepon, txtAlamat1, txtAlamat2;
    private JTextArea txtAlamat;
    private JComboBox<String> cmbTanggal, cmbBulan, cmbTahun, cmbStatus;
    private JRadioButton rbLaki, rbPerempuan;
    private JButton btnSimpan, btnReset, btnLihatData, btnHapus;

    // Penyimpanan data (ArrayList sebagai "database" sementara)
    private ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();

    // Komponen tabel untuk menampilkan data
    private JTable tabelKaryawan;
    private DefaultTableModel modelTabel;

    // ==================== CONSTRUCTOR ====================
    public TUGAS2_PBO_230401070204_MUHAMMAD_KETSAR_ALI_ABI_WAHID() {
        // Setup frame utama
        setTitle("Form Input Data Karyawan - Tugas 2 PBO - Muhammad Ketsar Ali Abi Wahid");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout utama: Form di kiri, Tabel di kanan
        setLayout(new BorderLayout(10, 10));

        // Panel Form (Kiri)
        JPanel panelForm = buatPanelForm();

        // Panel Tabel (Kanan)
        JPanel panelTabel = buatPanelTabel();

        // Split Pane untuk membagi layar
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelForm, panelTabel);
        splitPane.setDividerLocation(450);
        splitPane.setResizeWeight(0.4);

        add(splitPane, BorderLayout.CENTER);

        // Setup event handlers
        setupEventHandlers();
    }

    // ==================== METHOD BUAT PANEL FORM ====================
    private JPanel buatPanelForm() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                        " INPUT DATA KARYAWAN ",
                        0, 0,
                        new Font("Arial", Font.BOLD, 14),
                        new Color(70, 130, 180)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        mainPanel.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // NIK
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("NIK:"), gbc);
        txtNIK = new JTextField(18);
        txtNIK.setToolTipText("Masukkan NIK (hanya angka)");
        gbc.gridx = 1;
        mainPanel.add(txtNIK, gbc);

        // Nama
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Nama:"), gbc);
        txtNama = new JTextField(18);
        txtNama.setToolTipText("Masukkan nama lengkap");
        gbc.gridx = 1;
        mainPanel.add(txtNama, gbc);

        // Alamat
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Alamat:"), gbc);
        txtAlamat = new JTextArea(3, 18);
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);
        gbc.gridx = 1;
        mainPanel.add(scrollAlamat, gbc);

        // Tempat Lahir
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Tempat Lahir:"), gbc);
        txtTempatLahir = new JTextField(18);
        gbc.gridx = 1;
        mainPanel.add(txtTempatLahir, gbc);

        // Tanggal Lahir
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Tanggal Lahir:"), gbc);

        JPanel panelTglLahir = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        panelTglLahir.setBackground(new Color(240, 248, 255));

        String[] tanggal = new String[31];
        for (int i = 0; i < 31; i++)
            tanggal[i] = String.format("%02d", i + 1);
        cmbTanggal = new JComboBox<>(tanggal);

        String[] bulan = { "Jan", "Feb", "Mar", "Apr", "Mei", "Jun",
                "Jul", "Ags", "Sep", "Okt", "Nov", "Des" };
        cmbBulan = new JComboBox<>(bulan);

        String[] tahun = new String[56];
        for (int i = 0; i < 56; i++)
            tahun[i] = String.valueOf(1970 + i);
        cmbTahun = new JComboBox<>(tahun);

        panelTglLahir.add(cmbTanggal);
        panelTglLahir.add(new JLabel("-"));
        panelTglLahir.add(cmbBulan);
        panelTglLahir.add(new JLabel("-"));
        panelTglLahir.add(cmbTahun);
        gbc.gridx = 1;
        mainPanel.add(panelTglLahir, gbc);

        // Jenis Kelamin
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Jenis Kelamin:"), gbc);

        JPanel panelJK = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelJK.setBackground(new Color(240, 248, 255));
        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");
        rbLaki.setBackground(new Color(240, 248, 255));
        rbPerempuan.setBackground(new Color(240, 248, 255));

        ButtonGroup bgJK = new ButtonGroup();
        bgJK.add(rbLaki);
        bgJK.add(rbPerempuan);

        panelJK.add(rbLaki);
        panelJK.add(rbPerempuan);
        gbc.gridx = 1;
        mainPanel.add(panelJK, gbc);

        // Telepon
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Telepon:"), gbc);
        txtTelepon = new JTextField(18);
        txtTelepon.setToolTipText("Masukkan nomor telepon (hanya angka)");
        gbc.gridx = 1;
        mainPanel.add(txtTelepon, gbc);

        // Status Pernikahan
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Status:"), gbc);
        String[] statusList = { "Belum Menikah", "Menikah", "Cerai Hidup", "Cerai Mati" };
        cmbStatus = new JComboBox<>(statusList);
        gbc.gridx = 1;
        mainPanel.add(cmbStatus, gbc);

        // Alamat 1
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Alamat 1:"), gbc);
        txtAlamat1 = new JTextField(18);
        gbc.gridx = 1;
        mainPanel.add(txtAlamat1, gbc);

        // Alamat 2
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        mainPanel.add(createLabel("Alamat 2:"), gbc);
        txtAlamat2 = new JTextField(18);
        gbc.gridx = 1;
        mainPanel.add(txtAlamat2, gbc);

        // Panel Tombol
        row++;
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelButton.setBackground(new Color(240, 248, 255));

        btnSimpan = createButton("Simpan", new Color(40, 167, 69));
        btnReset = createButton("Reset", new Color(255, 193, 7));
        btnReset.setForeground(Color.BLACK);

        panelButton.add(btnSimpan);
        panelButton.add(btnReset);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        mainPanel.add(panelButton, gbc);

        return mainPanel;
    }

    // ==================== METHOD BUAT PANEL TABEL ====================
    private JPanel buatPanelTabel() {
        JPanel panelTabel = new JPanel(new BorderLayout(5, 5));
        panelTabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                        " DATA KARYAWAN TERSIMPAN ",
                        0, 0,
                        new Font("Arial", Font.BOLD, 14),
                        new Color(70, 130, 180)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panelTabel.setBackground(new Color(248, 249, 250));

        // Kolom tabel
        String[] kolom = { "NIK", "Nama", "Alamat", "TTL", "Jenis Kelamin",
                "Telepon", "Status", "Alamat 1", "Alamat 2" };

        // Model tabel (tidak bisa diedit langsung)
        modelTabel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Semua cell tidak bisa diedit
            }
        };

        tabelKaryawan = new JTable(modelTabel);
        tabelKaryawan.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabelKaryawan.setRowHeight(25);
        tabelKaryawan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelKaryawan.setGridColor(new Color(200, 200, 200));

        // Header styling
        JTableHeader header = tabelKaryawan.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        // Auto resize kolom
        tabelKaryawan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabelKaryawan.getColumnModel().getColumn(0).setPreferredWidth(100); // NIK
        tabelKaryawan.getColumnModel().getColumn(1).setPreferredWidth(120); // Nama
        tabelKaryawan.getColumnModel().getColumn(2).setPreferredWidth(150); // Alamat
        tabelKaryawan.getColumnModel().getColumn(3).setPreferredWidth(130); // TTL
        tabelKaryawan.getColumnModel().getColumn(4).setPreferredWidth(90); // JK
        tabelKaryawan.getColumnModel().getColumn(5).setPreferredWidth(110); // Telepon
        tabelKaryawan.getColumnModel().getColumn(6).setPreferredWidth(100); // Status
        tabelKaryawan.getColumnModel().getColumn(7).setPreferredWidth(100); // Alamat 1
        tabelKaryawan.getColumnModel().getColumn(8).setPreferredWidth(100); // Alamat 2

        JScrollPane scrollTabel = new JScrollPane(tabelKaryawan);
        scrollTabel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelTabel.add(scrollTabel, BorderLayout.CENTER);

        // Panel tombol tabel
        JPanel panelTombolTabel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelTombolTabel.setBackground(new Color(248, 249, 250));

        btnHapus = createButton("Hapus Data Terpilih", new Color(220, 53, 69));
        btnLihatData = createButton("Refresh Tabel", new Color(23, 162, 184));

        panelTombolTabel.add(btnHapus);
        panelTombolTabel.add(btnLihatData);

        // Label jumlah data
        JLabel lblInfo = new JLabel("Klik baris tabel untuk memilih, lalu klik 'Hapus' untuk menghapus.");
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblInfo.setForeground(Color.GRAY);
        panelTombolTabel.add(lblInfo);

        panelTabel.add(panelTombolTabel, BorderLayout.SOUTH);

        return panelTabel;
    }

    // ==================== HELPER METHODS ====================
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return label;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        // Fix agar warna button muncul di Windows
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });

        return btn;
    }

    // ==================== VALIDASI METHODS ====================
    /**
     * Method untuk mengecek apakah string hanya berisi angka
     *
     * @param str String yang akan dicek
     * @return true jika hanya angka, false jika ada karakter lain
     */
    private boolean isAngkaSaja(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        // Menggunakan regex untuk cek angka saja
        return str.trim().matches("\\d+");
    }

    /**
     * Method untuk mengecek apakah string hanya berisi huruf dan spasi
     *
     * @param str String yang akan dicek
     * @return true jika hanya huruf dan spasi
     */
    private boolean isNamaValid(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        // Mengizinkan huruf, spasi, dan tanda petik (untuk nama seperti O'Brien)
        return str.trim().matches("[a-zA-Z\\s'.]+");
    }

    // ==================== EVENT HANDLERS ====================
    private void setupEventHandlers() {
        // Tombol SIMPAN
        btnSimpan.addActionListener(e -> simpanData());

        // Tombol RESET
        btnReset.addActionListener(e -> resetForm());

        // Tombol HAPUS
        btnHapus.addActionListener(e -> hapusData());

        // Tombol REFRESH
        btnLihatData.addActionListener(e -> refreshTabel());
    }

    // Method untuk menyimpan data dengan VALIDASI LENGKAP
    private void simpanData() {
        // ========== VALIDASI NIK ==========
        String nikInput = txtNIK.getText().trim();

        if (nikInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "NIK tidak boleh kosong!",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtNIK.requestFocus();
            return;
        }

        if (!isAngkaSaja(nikInput)) {
            JOptionPane.showMessageDialog(this,
                    "NIK harus berupa angka saja!\nContoh: 1234567890",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtNIK.requestFocus();
            txtNIK.selectAll();
            return;
        }

        // ========== VALIDASI NAMA ==========
        String namaInput = txtNama.getText().trim();

        if (namaInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama tidak boleh kosong!",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return;
        }

        if (!isNamaValid(namaInput)) {
            JOptionPane.showMessageDialog(this,
                    "Nama hanya boleh berisi huruf dan spasi!\nContoh: Muhammad Ketsar",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            txtNama.selectAll();
            return;
        }

        // ========== VALIDASI TELEPON ==========
        String teleponInput = txtTelepon.getText().trim();

        if (teleponInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nomor Telepon tidak boleh kosong!",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtTelepon.requestFocus();
            return;
        }

        if (!isAngkaSaja(teleponInput)) {
            JOptionPane.showMessageDialog(this,
                    "Nomor Telepon harus berupa angka saja!\nContoh: 081234567890",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtTelepon.requestFocus();
            txtTelepon.selectAll();
            return;
        }

        // Validasi panjang telepon (minimal 10 digit, maksimal 15 digit)
        if (teleponInput.length() < 10 || teleponInput.length() > 15) {
            JOptionPane.showMessageDialog(this,
                    "Nomor Telepon harus 10-15 digit!\nContoh: 081234567890",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            txtTelepon.requestFocus();
            txtTelepon.selectAll();
            return;
        }

        // ========== VALIDASI JENIS KELAMIN ==========
        if (!rbLaki.isSelected() && !rbPerempuan.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Pilih jenis kelamin!",
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
            rbLaki.requestFocus();
            return;
        }

        // ========== CEK NIK DUPLIKAT ==========
        for (Karyawan k : daftarKaryawan) {
            if (k.getNik().equals(nikInput)) {
                JOptionPane.showMessageDialog(this,
                        "NIK " + nikInput + " sudah terdaftar!\nGunakan NIK yang berbeda.",
                        "Data Duplikat", JOptionPane.WARNING_MESSAGE);
                txtNIK.requestFocus();
                txtNIK.selectAll();
                return;
            }
        }

        // ========== SEMUA VALIDASI BERHASIL, SIMPAN DATA ==========
        String jenisKelamin = rbLaki.isSelected() ? "Laki-laki" : "Perempuan";

        String tglLahir = cmbTanggal.getSelectedItem() + "-" +
                cmbBulan.getSelectedItem() + "-" +
                cmbTahun.getSelectedItem();

        // Buat objek Karyawan baru
        Karyawan karyawanBaru = new Karyawan(
                nikInput,
                namaInput,
                txtAlamat.getText().trim(),
                txtTempatLahir.getText().trim(),
                tglLahir,
                jenisKelamin,
                teleponInput,
                (String) cmbStatus.getSelectedItem(),
                txtAlamat1.getText().trim(),
                txtAlamat2.getText().trim());

        // Simpan ke ArrayList
        daftarKaryawan.add(karyawanBaru);

        // Tambahkan ke tabel
        modelTabel.addRow(karyawanBaru.toArray());

        // Notifikasi sukses
        JOptionPane.showMessageDialog(this,
                "✅ Data berhasil disimpan!\n\n" +
                        "NIK: " + nikInput + "\n" +
                        "Nama: " + namaInput + "\n\n" +
                        "Total data: " + daftarKaryawan.size() + " karyawan",
                "Sukses", JOptionPane.INFORMATION_MESSAGE);

        // Reset form setelah simpan
        resetForm();
    }

    // Method untuk reset form
    private void resetForm() {
        txtNIK.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtTempatLahir.setText("");
        txtTelepon.setText("");
        txtAlamat1.setText("");
        txtAlamat2.setText("");
        cmbTanggal.setSelectedIndex(0);
        cmbBulan.setSelectedIndex(0);
        cmbTahun.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        rbLaki.setSelected(false);
        rbPerempuan.setSelected(false);
        txtNIK.requestFocus();
    }

    // Method untuk hapus data
    private void hapusData() {
        int selectedRow = tabelKaryawan.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih baris data yang ingin dihapus terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ambil data untuk konfirmasi
        String nikTerpilih = (String) modelTabel.getValueAt(selectedRow, 0);
        String namaTerpilih = (String) modelTabel.getValueAt(selectedRow, 1);

        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus data ini?\n\n" +
                        "NIK: " + nikTerpilih + "\n" +
                        "Nama: " + namaTerpilih,
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            // Hapus dari ArrayList dan tabel
            daftarKaryawan.remove(selectedRow);
            modelTabel.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this,
                    "✅ Data berhasil dihapus!\n\nSisa data: " + daftarKaryawan.size() + " karyawan",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method untuk refresh tabel
    private void refreshTabel() {
        // Kosongkan tabel
        modelTabel.setRowCount(0);

        // Isi ulang dari ArrayList
        for (Karyawan k : daftarKaryawan) {
            modelTabel.addRow(k.toArray());
        }

        JOptionPane.showMessageDialog(this,
                "🔄 Tabel berhasil di-refresh!\n\nTotal data: " + daftarKaryawan.size() + " karyawan",
                "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // ==================== MAIN METHOD ====================
    public static void main(String[] args) {
        // Jalankan GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TUGAS2_PBO_230401070204_MUHAMMAD_KETSAR_ALI_ABI_WAHID form = new TUGAS2_PBO_230401070204_MUHAMMAD_KETSAR_ALI_ABI_WAHID();
            form.setVisible(true);
        });
    }
}
