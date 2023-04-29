package exercises;
import java.util.regex.*;

public class Notebook 
{

    public Notebook(String strToParse) // Конструктор класса Notebook
    {
        setPrice(Math.random() * 1000); // генерируем цену от 0 до 1000
        brand = findRegex(strToParse, "(?<=Ноутбук\\s)[a-zA-Z]*(?=\\s)");
        model = findRegex(strToParse, "(?<=" + brand + "\\s).*(?=\\s[а-я]*\\s\\[)");
        color = findRegex(strToParse, "(?<=\\s)[а-яА-Я]*(?=\\s\\[)");
        size = Double.parseDouble(findRegex(strToParse, "[0-9]{1,2}.?[0-9]?(?=\\\")"));
        String[] paramsRAW = findRegex(strToParse, "(?<=\\[).*(?=\\])").split(", ");
        resolution = paramsRAW[0];
        matrixType = paramsRAW[1];
        cpuFamily = paramsRAW[2];
        cpuCores = Integer.parseInt(findRegex(paramsRAW[3], "(?<=ядра\\:\\s)[0-9]{1,2}(?=\\sх)"));
        cpuFrequency = Double.parseDouble(findRegex(paramsRAW[3], "(?<=х\\s).*(?=\\sГГц)"));
        volRAM = Integer.parseInt(findRegex(paramsRAW[4], "(?<=RAM\\s)[0-9]{1,3}(?=\\sГБ)"));
        typeHDD = findRegex(paramsRAW[5], "[a-zA-Z]{1,}");
        volHDD = Integer.parseInt(findRegex(paramsRAW[5], "[0-9]{1,}"));
        videoAdaptor = paramsRAW[6];
        osPreinstall = paramsRAW[7];
    }

    public void setPrice(Double inPrice) 
    {
        price = inPrice;
    }

    public Boolean isMatchResolution(String inResolution) // Поиск по разрешению экрана 
    {
        return findStr(resolution, inResolution);
    }

    public Boolean isMatchCPUType(String inCpu) // Поиск по типу процессора
    {
        return findStr(cpuFamily, inCpu);
    }

    public Boolean isMatchOSType(String inOS) // Поиск по виду Операционной системы
    {
        return findStr(osPreinstall, inOS);
    }

    public Boolean isMatchPrice(Double minPrice, Double maxPrice) // Поиск по ценовому диапазону
    {
        return price >= minPrice && price <= maxPrice; // Минимальная и максимальная цена товара
    }

    public Boolean isMatchRAM(Integer minRAM, Integer maxRAM) // Поиск по объему Оперативной памяти
    {
        return volRAM >= minRAM && volRAM <= maxRAM; // Минимальный и максимальный объем планки
    }

    private String findRegex(String inStr, String regex) // Поиск регулярного выражения в строке
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inStr);
        return matcher.find() ? matcher.group() : "";
    }

    private Boolean findStr(String par, String inStr) // Проверка наличия в строке par содержимого inStr
    {
        Pattern pattern = Pattern.compile(inStr.replace(" ", "").toLowerCase());
        Matcher matcher = pattern.matcher(par.replace(" ", "").toLowerCase());
        return matcher.find();
    }

    private Double price; // цена
    private String brand; // бренд
    private String model; // модель
    private String color; // цвет
    private Double size; // диагональ
    private String resolution; // разрешение
    private String matrixType; // тип матрицы
    private String cpuFamily; // марка ЦПУ
    private Integer cpuCores; // количество ядер
    private Double cpuFrequency; // частота ЦПУ
    private Integer volRAM; // объем оперативной памяти
    private String typeHDD; // тип накопителя
    private Integer volHDD; // объем накопителя
    private String videoAdaptor; // марка видеокарты
    private String osPreinstall; // операционная система

    @Override
    public String toString() 
    {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
