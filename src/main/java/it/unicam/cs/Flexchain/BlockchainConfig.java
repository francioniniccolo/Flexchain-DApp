package it.unicam.cs.Flexchain;

public  class BlockchainConfig {

    private static BlockchainConfig instance = null;

    public String MONITOR_ADDRESS;
    public String PRIVATE_KEY;


    private BlockchainConfig(){
        MONITOR_ADDRESS="0x25E9A9981682Fc4C6fE4BaECD7b3746aBc7EeD2e";
        PRIVATE_KEY="80a28a9b8694d1dd909680ac4b342ee5276b96b241a9784f41e52c8686528205";
    }

    public static BlockchainConfig getInstance()
    {
        if (instance == null)
            instance = new BlockchainConfig();

        return instance;
    }

}
