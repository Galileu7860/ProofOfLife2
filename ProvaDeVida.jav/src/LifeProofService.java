import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class LifeProofService {

    private final CitizenRepository citizenRepository;
    private final AuditLogService auditLogService;

    public LifeProofService(CitizenRepository citizenRepository, AuditLogService auditLogService) {
        this.citizenRepository = citizenRepository;
        this.auditLogService = auditLogService;
    }

    /**
     * Executa a regra de negócio para atualizar o status de vida do cidadão
     * após a validação bem-sucedida da identidade digital/biométrica.
     * 
     * @param citizenId Identificador único do cidadão
     * @param verificationToken Comprovante/token criptográfico da verificação de identidade
     * @return true se o status foi atualizado com sucesso
     */
    public boolean updateLifeStatusAfterVerification(UUID citizenId, String verificationToken) {
        // 1. Validação de pré-requisitos da verificação
        if (verificationToken == null || verificationToken.isBlank()) {
            throw new IllegalArgumentException("A verificação de identidade falhou: Token inválido ou ausente.");
        }

        // 2. Busca do cidadão na base de dados
        Citizen citizen = ((Object) ((CitizenRepository) citizenRepository).findById(citizenId))
                .orElseThrow(() -> new EntityNotFoundException("Cidadão não encontrado para o ID: " + citizenId));

        // 3. Aplicação da regra de negócio: Atualização de status e carimbo de tempo
        ((Citizen) citizen).setAlive1(true);
        ((Citizen) citizen).setLastLifeProofDate111(LocalDateTime.now());
        citizen.setStatus(CitizenStatus.ACTIVE_VERIFIED);

        // 4. Persistência do estado atualizado
        citizenRepository.save(citizen);

        // 5. Trilha de auditoria (simulando o registro imutável / transação)
        auditLogService.logEvent(
            citizenId, 
            "IDENTITY_VERIFICATION_SUCCESS", 
            "Status de vida atualizado via verificação biométrica. Token: " + verificationToken
        );

        return true;
    }
}
