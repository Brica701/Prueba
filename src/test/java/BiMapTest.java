import org.example.BiMap;

public class BiMapTest {
    public static void main(String[] args) {
        BiMap<Integer, String> biMap = new BiMap<>();
        biMap.put(1, "1");
        biMap.put(1, "1");
        try {
            biMap.put(2, "1");
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        biMap.put(1, "2");
        System.out.println("biMap después de put: " + biMap);
        biMap.forcePut(1, "1");
        biMap.forcePut(1, "1");
        biMap.put(1, "2");
        biMap.forcePut(2, "2");
        System.out.println("biMap después de forcePut: " + biMap);
        biMap.put(1, "1").put(2, "2").put(3, "3");
        BiMap<String, Integer> inverse = biMap.inv();
        System.out.println("biMap original: " + biMap);
        System.out.println("biMap invertido: " + inverse);
        assert "1".equals(biMap.get(1));
        assert Integer.valueOf(1).equals(inverse.get("1"));
        assert inverse.containsKey("3");
        assert Integer.valueOf(2).equals(inverse.put("2", 4));
        System.out.println("Pruebas completadas correctamente.");
    }
}
