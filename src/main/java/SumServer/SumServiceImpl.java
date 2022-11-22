package SumServer;


import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse>
            responseObserver) {
// Block 1: extract the data required
        Sum sum = request.getSum();
        String firstNum = sum.getFirstNum();
        String lastNum = sum.getLastNum();
// Block 2: create the response message
        int summary = Integer.parseInt(firstNum)+Integer.parseInt(lastNum);
        String result = "Server Output: " + firstNum + " + " + lastNum+" = "+summary;
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();
// Block 3: send the response
        responseObserver.onNext(response);
// Block 4: complete the RPC call
        responseObserver.onCompleted();
    }
}
